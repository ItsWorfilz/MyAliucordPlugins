package me.aniimalz.plugins

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliucord.Http
import com.aliucord.Logger
import com.aliucord.Utils
import com.aliucord.fragments.SettingsPage
import com.aliucord.utils.GsonUtils
import com.aliucord.utils.ReflectUtils
import com.aliucord.utils.RxUtils.subscribe
import com.aliucord.wrappers.GuildRoleWrapper.Companion.color
import com.aliucord.wrappers.GuildRoleWrapper.Companion.icon
import com.aliucord.wrappers.GuildRoleWrapper.Companion.name
import com.discord.api.role.GuildRole
import com.discord.stores.StoreStream
import com.discord.utilities.analytics.AnalyticSuperProperties
import com.discord.utilities.rest.RestAPI
import com.discord.utilities.rx.ObservableExtensionsKt
import com.facebook.drawee.view.SimpleDraweeView
import com.lytefast.flexinput.R
import rx.Subscription

var rmSub: Subscription? = null
val loggerd = Logger("RMP")

class RoleMembersPage(private val role: GuildRole, private val guild: Long) : SettingsPage() {
    private val fetchedRoles =
        mutableMapOf<Long, MutableList<Long>>() // id of role & list of user ids with that role
    var recycler: RecyclerView? = null

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewBound(view: View) {
        super.onViewBound(view)

        setActionBarTitle("Role Members")
        setActionBarSubtitle(role.name)
        val ctx = requireContext()

        SimpleDraweeView(ctx).apply {
            layoutParams = LinearLayout.LayoutParams(128, 128).apply { gravity = Gravity.CENTER }
            setImageURI("https://cdn.discordapp.com/role-icons/${role.id}/${role.icon}.png?size=128")
            if (role.icon != null) addView(this)
        }

        if (shouldFetch(role.id)) {
            loggerd.info("Fetching role members...")
            Utils.threadPool.execute {
                val h = Http.Request("https://discord.com/api/v9/guilds/$guild/roles/${role.id}/member-ids", "GET")
                    .setHeader("Authorization", ReflectUtils.getField(StoreStream.getAuthentication(), "authToken") as String?)
                    .setHeader("User-Agent", RestAPI.AppHeadersProvider.INSTANCE.userAgent)
                    .setHeader("X-Super-Properties", AnalyticSuperProperties.INSTANCE.superPropertiesStringBase64)
                    .setHeader("Accept", "*/*").execute()
                if (h.ok()) {
                    fetchedRoles[role.id] = h.json(mutableListOf<Long>()::class.java) as MutableList<Long>
                    updateList(ctx, h.json(mutableListOf<Long>()::class.java))
                }
            }
        } else {
            loggerd.info("Cache for role is present, updating list")
            fetchedRoles[role.id]?.let { updateList(ctx, it) }
        }

    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun updateList(ctx: Context, userList: MutableList<Long>) {
        loggerd.info("updating list")
        /*TextView(ctx, null, 0, R.i.UiKit_Settings_Item_Header).apply {
            text = "${role.name} • ${userList.size}"
            setTextColor(role.color)
            addView(this)
        }*/

        userList.forEach {
            loggerd.info(it.toString())
            StoreStream.getUsers().users[it]?.let { it1 -> MemberView(ctx, it1, guild) }
        }
    }

    private fun shouldFetch(roleId: Long): Boolean {
        return !fetchedRoles.containsKey(roleId)
    }
}