//package mk.ukim.finki.wp.lab.model;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class UserFullnameConverter implements AttributeConverter<mk.ukim.finki.wp.lab.model.UserFullname,String> {
//
//    @Override
//    public String convertToDatabaseColumn(mk.ukim.finki.wp.lab.model.UserFullname userFullname) {
//        if (userFullname == null) {
//            return null;
//        }
//        return userFullname.getName() + " " + userFullname.getSurname();
//
//    }
//
//    @Override
//    public mk.ukim.finki.wp.lab.model.UserFullname convertToEntityAttribute(String dbData) {
//        if (dbData == null) {
//            return null;
//        }
//        String[] parts = dbData.split(" ");
//        mk.ukim.finki.wp.lab.model.UserFullname fullname = new mk.ukim.finki.wp.lab.model.UserFullname();
//        fullname.setName(parts[0]);
//        fullname.setSurname(parts[1]);
//        return fullname;
//    }
//}
