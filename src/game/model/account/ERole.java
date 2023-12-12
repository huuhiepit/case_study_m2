package game.model.account;

public enum ERole {
    USER(1, "Người chơi"),
    ADMIN(2, "Quản lý");

    private long id;
    private String role;

    ERole(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public static ERole getBy(String role) {
        for(ERole eRole: ERole.values()) {
            if(eRole.getRole().equals(role)) {
                return eRole;
            }
        }
        return null;
    }

    public String getRole() {
        return role;
    }
}
