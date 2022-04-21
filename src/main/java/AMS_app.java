import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import model.Address;

public class AMS_app {

    public static void main(String[] args) {
        AddressDao addressDao =new AddressDaoImpl();


//        for (int i = 4; i < 8; i++) {
//            addressDao.createAddress(
//                    new Address(i,
//                            "Yerevan" + i,
//                            "Komitas"+i
//                    )
//            );
//        }

        addressDao.update(14, new Address("fghfhgf", "gfashgf"));
        System.out.println(addressDao.getAddressById(14));
    }
}
