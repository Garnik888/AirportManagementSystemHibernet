import dao.AddressDao;
import dao.impl.AddressDaoImpl;
import model.Address;

public class AMS_app {

    public static void main(String[] args) {
        AddressDao addressDao =new AddressDaoImpl();


        for (int i = 0; i < 10; i++) {
            addressDao.createAddress(
                    new Address(i,
                            "Yerevan" + i,
                            "Komitas"+i
                    )
            );
        }
    }
}
