package xyz.oskarnilsson;

public class DataValidate {
    private DBConnect connect = new DBConnect();

    public boolean Validate(String name, String lat, String longi, String nation) {
        if (name.length() > 45 || name.equals("")) {
            ErrorWindow errorWindow = new ErrorWindow("Name is longer than 45 characters or empty!");
            return false;
        }
        if (!lat.matches("-?([0-9]{1,3})+([.][0-9]{5,8})")) {
            ErrorWindow errorWindow = new ErrorWindow("Lat should have 5-8 digits after the decimal[.]!");
            return false;
        }
        if (Double.parseDouble(lat) > 180 || Double.parseDouble(lat) < -180) {
            ErrorWindow errorWindow = new ErrorWindow("Lat should be between 180.00000 and -180.00000!");
            return false;
        }

        if (!lat.matches("-?([0-9]{1,3})+([.][0-9]{5,8})")) {
            ErrorWindow errorWindow = new ErrorWindow("Long should have 5-8 digits after the decimal");
            return false;
        }
        if (Double.parseDouble(longi) > 180 || Double.parseDouble(longi) < -180) {
            ErrorWindow errorWindow = new ErrorWindow("Long should be between 180.00000 and -180.00000!");
            return false;
        }

        if (nation.length() > 45) {
            ErrorWindow errorWindow = new ErrorWindow("Nation is longer than 45 characters!");
            return false;
        }
        if (connect.DBConnectNationID(nation) == 0) {
            ErrorWindow errorWindow = new ErrorWindow("Nation not found in database!");
            return false;
        }


        return true;
    }
}
