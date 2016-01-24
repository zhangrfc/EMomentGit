package zhangrf.emoment;

/**
 * Created by tengyuanye on 1/23/16.
 */
import android.view.View;

public class DefaultTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }

}
