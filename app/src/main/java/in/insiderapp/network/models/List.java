
package in.insiderapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("masterList")
    @Expose
    private MasterList masterList;
    @SerializedName("groupwiseList")
    @Expose
    private GroupwiseList groupwiseList;

    public MasterList getMasterList() {
        return masterList;
    }

    public void setMasterList(MasterList masterList) {
        this.masterList = masterList;
    }

    public GroupwiseList getGroupwiseList() {
        return groupwiseList;
    }

    public void setGroupwiseList(GroupwiseList groupwiseList) {
        this.groupwiseList = groupwiseList;
    }

}