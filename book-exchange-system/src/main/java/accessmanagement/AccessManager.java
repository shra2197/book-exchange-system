package accessmanagement;

import enums.Permission;
import enums.UserRole;

public class AccessManager {
    public static boolean hasPermission(UserRole userRole, Permission permission){
        switch (userRole){
            case GUEST:
                return permission == Permission.BROWSE_BOOKS;
            case VERIFIED:
                return permission == Permission.BROWSE_BOOKS || permission == Permission.LIST_BOOKS || permission == Permission.EXCHANGE_BOOKS;
            case ADMIN:
                return true;
            default:
                return false;
        }
    }
}
