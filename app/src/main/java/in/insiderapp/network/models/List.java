
package in.insiderapp.network.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("masterList")
    @Expose
//    private MasterList masterList;
    private JsonObject masterList;
    @SerializedName("groupwiseList")
    @Expose
//    private GroupwiseList groupwiseList;
    private JsonObject groupwiseList;

    public JsonObject getMasterList() {
        return masterList;
    }

    public void setMasterList(JsonObject masterList) {
        this.masterList = masterList;
    }

    public JsonObject getGroupwiseList() {
        return groupwiseList;
    }

    public void setGroupwiseList(JsonObject groupwiseList) {
        this.groupwiseList = groupwiseList;
    }

}
