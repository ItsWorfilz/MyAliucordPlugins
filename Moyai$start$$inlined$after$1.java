package me.aniimalz.plugins;

import com.aliucord.Logger;
import com.aliucord.api.PatcherAPI;
import com.discord.api.message.reaction.MessageReactionUpdate;
import com.discord.stores.StoreMessageReactions;
import com.discord.stores.StoreStream;
import de.robv.android.xposed.XC_MethodHook;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006¸\u0006\u0000"}, d2 = {"com/aliucord/patcher/PatcherExtensionsKt$after$2", "Lde/robv/android/xposed/XC_MethodHook;", "afterHookedMethod", "", "param", "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;", "Aliucord_debug"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: PatcherExtensions.kt */
public final class Moyai$start$$inlined$after$1 extends XC_MethodHook {
    final /* synthetic */ PatcherAPI $this_after;
    final /* synthetic */ Moyai this$0;

    public Moyai$start$$inlined$after$1(PatcherAPI $receiver, Moyai moyai) {
        this.$this_after = $receiver;
        this.this$0 = moyai;
    }

    /* access modifiers changed from: protected */
    public void afterHookedMethod(@NotNull XC_MethodHook.MethodHookParam param) {
        try {
            Object obj = param.thisObject;
            if (obj != null) {
                StoreMessageReactions storeMessageReactions = (StoreMessageReactions) obj;
                Object obj2 = param.args[0];
                if (obj2 != null) {
                    MessageReactionUpdate r = (MessageReactionUpdate) obj2;
                    if (Intrinsics.areEqual(r.b().d(), "🗿")) {
                        if (r.a() == StoreStream.getChannelsSelected().getId()) {
                            this.this$0.funny();
                            return;
                        }
                        return;
                    }
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.discord.api.message.reaction.MessageReactionUpdate");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.discord.stores.StoreMessageReactions");
        } catch (Throwable th) {
            Logger logger = this.$this_after.logger;
            String name = param.method.getDeclaringClass().getName();
            String name2 = param.method.getName();
            logger.error("Exception while hooking " + name + "." + name2, th);
        }
    }
}
