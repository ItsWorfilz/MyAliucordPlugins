package me.aniimalz.plugins;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import com.aliucord.Utils;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.api.PatcherAPI;
import com.aliucord.entities.Plugin;
import com.aliucord.utils.RxUtils;
import com.discord.api.message.reaction.MessageReactionUpdate;
import com.discord.stores.StoreMessageReactions;
import com.discord.stores.StoreStream;
import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.Subscription;

@AliucordPlugin
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lme/aniimalz/plugins/Moyai;", "Lcom/aliucord/entities/Plugin;", "()V", "observable", "Lrx/Subscription;", "funny", "", "start", "ctx", "Landroid/content/Context;", "stop", "Moyai_debug"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: Moyai.kt */
public final class Moyai extends Plugin {
    @Nullable
    private Subscription observable;

    public void start(@NotNull Context ctx) {
        PatcherAPI $this$after$iv = this.patcher;
        Class[] paramTypes$iv = {MessageReactionUpdate.class};
        $this$after$iv.patch(StoreMessageReactions.class.getDeclaredMethod("handleReactionAdd", (Class[]) Arrays.copyOf(paramTypes$iv, paramTypes$iv.length)), new Moyai$start$$inlined$after$1($this$after$iv, this));
        this.observable = RxUtils.subscribe(RxUtils.onBackpressureBuffer(StoreStream.getGatewaySocket().getMessageCreate()), new Moyai$start$2(this));
    }

    /* access modifiers changed from: private */
    public final void funny() {
        try {
            Utils.threadPool.execute(Moyai$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: funny$lambda-2  reason: not valid java name */
    public static final void m0funny$lambda2() {
        MediaPlayer $this$funny_u24lambda_u2d2_u24lambda_u2d1 = new MediaPlayer();
        $this$funny_u24lambda_u2d2_u24lambda_u2d1.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).setUsage(1).build());
        $this$funny_u24lambda_u2d2_u24lambda_u2d1.setDataSource("https://raw.githubusercontent.com/ItzOnlyAnimal/AliuPlugins/main/boom.ogg");
        $this$funny_u24lambda_u2d2_u24lambda_u2d1.prepare();
        $this$funny_u24lambda_u2d2_u24lambda_u2d1.start();
    }

    public void stop(@NotNull Context ctx) {
        this.patcher.unpatchAll();
        Subscription subscription = this.observable;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
