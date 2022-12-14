package hari.core.graphql.helper;

import org.json.JSONException;
import org.json.JSONObject;

public class UserJwtData {
    private String userId;
    private String identity;
    private String name;
    private String email;
    private String photo;
    private String unitId;

    public UserJwtData(JSONObject userdata) throws JSONException {
        JSONObject userUnit = new JSONObject(userdata.getString("user_unit"));
        this.setUserId(userdata.getString("user_id"));
        this.setIdentity(userdata.getString("identity"));
        this.setName(userdata.getString("name"));
        this.setEmail(userdata.getString("email"));
        this.setPhoto(userUnit.getString("photo"));
        this.setUnitId(userUnit.getString("unit_id"));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "UserJwtData{" +
                "userId='" + userId + '\'' +
                ", identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", unitId='" + unitId + '\'' +
                '}';
    }
}
