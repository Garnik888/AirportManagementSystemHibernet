package util;

import dao.impl.AddressDaoImpl;
import model.Address;
import model.Company;
import model.Passenger;
import service.impl.CompanyServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class CreatDBFromFile {

    public void creatComp(String path) {

        CompanyServiceImpl csi = new CompanyServiceImpl();
        Company com = new Company();
        String[] words;
        String[] comData;

        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {

            while (br.read() != -1) {

                words = br.readLine().split(",");
                com.setCompanyName(words[0]);
                comData = words[1].split("/");
                if(comData[0].length() != 2) {
                    if(comData[1].length() != 2) {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-0" +
                                comData[0] + "-0" + comData[1]));
                    } else {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-0" +
                                comData[0] + "-" + comData[1]));
                    }

                } else {

                    if(comData[1].length() != 2) {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-" +
                                comData[0] + "-0" + comData[1]));
                    } else {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-" +
                                comData[0] + "-" + comData[1]));
                    }
                }


                csi.save(com);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatAddress(String path) {

        AddressDaoImpl adi = new AddressDaoImpl();
        Address address = new Address();
        String[] words;

        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {

            while (br.readLine() != null) {

                words = br.readLine().split(",");
                address.setCountry(words[2]);
                address.setCity(words[3]);

                adi.createAddress(address);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
