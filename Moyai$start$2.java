package me.aniimalz.plugins;

import com.discord.api.message.Message;
import com.discord.stores.StoreStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\n \u0003*\u0004\u0018\u00010\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/discord/api/message/Message;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 5, 1}, xi = 48)
/* compiled from: Moyai.kt */
final class Moyai$start$2 extends Lambda implements Function1<Message, Unit> {
    final /* synthetic */ Moyai this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Moyai$start$2(Moyai moyai) {
        super(1);
        this.this$0 = moyai;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object p1) {
        invoke((Message) p1);
        return Unit.INSTANCE;
    }

    public final void invoke(Message $this$subscribe) {
        if ($this$subscribe != null) {
            com.discord.models.message.Message message = new com.discord.models.message.Message($this$subscribe);
            String content = message.getContent();
            if (content != null) {
                String content2 = content.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(content2, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                if (message.getChannelId() == StoreStream.getChannelsSelected().getId()) {
                    if (StringsKt.contains$default(content2, "meow", false, 2, (Object) null) || StringsKt.contains$default(content2, "Meow", false, 2, (Object) null)) {
                        this.this$0.funny();
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
    }
}
