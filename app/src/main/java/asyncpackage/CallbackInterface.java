package asyncpackage;

import org.json.JSONArray;

/**
 * Created by formation on 03/11/2017.
 */
public interface CallbackInterface {
    void onTaskFinished(JSONArray result);
//    void onTaskInDoing(JSONArray result);
}
