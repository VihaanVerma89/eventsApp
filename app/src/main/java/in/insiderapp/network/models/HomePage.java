
package in.insiderapp.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePage {

    @SerializedName("list")
    @Expose
    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
