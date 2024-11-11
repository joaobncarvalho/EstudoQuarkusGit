package my.groupId.jongue.rest.dto;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import my.groupId.quarkustest.domain.user.Follower;

import java.util.List;

public class FollowersPerUserResponse {

    private Integer followersCount;
    private List<FollowerResponse> content;

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public List<FollowerResponse> getContent() {
        return content;
    }

    public void setContent(List<FollowerResponse> content) {
        this.content = content;
    }
}
