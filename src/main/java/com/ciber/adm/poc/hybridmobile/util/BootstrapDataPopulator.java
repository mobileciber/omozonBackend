package com.ciber.adm.poc.hybridmobile.util;

import com.ciber.adm.poc.hybridmobile.domain.*;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


@Component
public class BootstrapDataPopulator implements InitializingBean {

    public static final String TEST_USER_USERNAME = "freddy";
    public static final String TEST_USER_PASSWORD = "krueger";

//    @SuppressWarnings({"UnusedDeclaration"})
//    private enum EnumCity {
//        BERGISCHGLADBACH("Bergisch Gladbach"),
//        BERLIN("Berlin"),
//        KOELN("K\u00f6ln"),
//        FRANKFURT("Frankfurt"),
//        LEVERKUSEN("Leverkusen"),
//        MUENCHEN("M\u00fcnchen"),
//        ROSENHEIM("Rosenheim");
//
//        private String name;
//
//        private EnumCity(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//    }

//    @SuppressWarnings({"UnusedDeclaration"})
//    private enum EnumCar {
//        // MINI
//        PEUGEOT207("Peugeot", "207 CC", CarType.MINI, BigDecimal.valueOf(81.46)),
//        SMART("smart", "fortwo Cabrio", CarType.MINI, BigDecimal.valueOf(70.52)),
//        VWFOX("VW", "Fox", CarType.MINI, BigDecimal.valueOf(65.45)),
//        // ECONOMY
//        BMW1("BMW", "1er", CarType.ECONOMY, BigDecimal.valueOf(87.23)),
//        BMW3("BMW", "3er Coup\u00e9", CarType.ECONOMY, BigDecimal.valueOf(98.45)),
//        CITROENC5("Citroen", "C5", CarType.ECONOMY, BigDecimal.valueOf(135.84)),
//        OPELASTRA("Opel", "Astra Caravan", CarType.ECONOMY, BigDecimal.valueOf(110.65)),
//        PEUGEOT308("Peugeot", "308", CarType.ECONOMY, BigDecimal.valueOf(95.78)),
//        SEATIBIZA("Seat", "Ibiza ST", CarType.ECONOMY, BigDecimal.valueOf(80.56)),
//        VOLVO("Volvo", "V60", CarType.ECONOMY, BigDecimal.valueOf(101.57)),
//        VWPASSAT("VW", "Passat Variant", CarType.ECONOMY, BigDecimal.valueOf(132.77)),
//        VWPOLO("VW", "Polo", CarType.ECONOMY, BigDecimal.valueOf(76.88)),
//        VWSCIROCCO("VW", "Scirocco", CarType.ECONOMY, BigDecimal.valueOf(125.00)),
//        // PREMIUM
//        AUDIA6("Audi", "A6", CarType.PREMIUM, BigDecimal.valueOf(155.54)),
//        BMW7("BMW", "7er", CarType.PREMIUM, BigDecimal.valueOf(170.36)),
//        BMWX5("BMW", "X5", CarType.PREMIUM, BigDecimal.valueOf(150.62)),
//        MERCEDESE("Mercedes-Benz", "E-Klasse", CarType.PREMIUM, BigDecimal.valueOf(145.94));
//
//        private String manufacturer;
//        private String name;
//        private CarType type;
//        private BigDecimal fee;
//
//        private EnumCar(String manufacturer, String name, CarType type, BigDecimal fee) {
//            this.manufacturer = manufacturer;
//            this.name = name;
//            this.type = type;
//            this.fee = fee;
//        }
//
//        public String getManufacturer() {
//            return manufacturer;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public CarType getCarType() {
//            return type;
//        }
//
//        public BigDecimal getFee() {
//            return fee;
//        }
//    }

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private Customer customer;
//    private Map<EnumCity, City> cities;
//    private List<Car> cars;

    @Override
    public void afterPropertiesSet() throws Exception {
        entityRepository.cleanup();
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                doPopulateTestDataInDb();
                status.flush();
                return null;
            }
        });
    }

    private void doPopulateTestDataInDb() {
        createCustomer();
        createStores();
//        createCities();
//        createCars();
//        createRentals();
    }

    private void createCustomer() {
        customer =  new Customer("Freddy Krueger", "freddy@nightmare.com", "Elm Street 1", "11111", "Springwood Ohio", new ArrayList<Order>());
        HybridmobileUserDetails user = new HybridmobileUserDetails(TEST_USER_USERNAME, TEST_USER_PASSWORD, customer);
        entityRepository.persist(user);
    }
    
    private void createStores(){
    	createStore("Schildergasse 387", "Cologne", 50667, 50.9363932, 6.9525044, "10:00-20:00", "9:00-20:00");
    	createStore("Kö 155", "Düsseldorf", 40215, 51.2176592, 6.7789186, "11:00-21:00", "9:00-22:00");
    	createStore("Kurfürstendamm 567", "Berlin", 10115, 52.499237, 13.366344, "12:00-20:00", "11:00-21:00");
    	createStore("Viktualienmarkt 789", "München", 80331, 48.1355235, 11.5763976, null, null);
//    	for(int i = 0; i < 10; i++){
//    		createStore("Store " + i, "opening times for Store " + i);
//    	}
    }
    
    private void createStore(String street, String city, int zipCode, double latitude, double longitude, String openingTimesMoFr, String openingTimesSa) {
    	Store store = new Store(street, city, zipCode, latitude, longitude, openingTimesMoFr, openingTimesSa);
    	entityRepository.persist(store);
    }

//    private void createCities() {
//        cities = new HashMap<EnumCity, City>();
//        for (EnumCity enumCity : EnumCity.values()) {
//            City city = new City(enumCity.getName());
//            entityRepository.persist(city);
//            cities.put(enumCity, city);
//        }
//    }
//
//    private void createCars() {
//        cars = new ArrayList<Car>();
//        for (Entry<EnumCity, City> entry : cities.entrySet()) {
//            City city = entry.getValue();
//            for (EnumCar enumCar: EnumCar.values()) {
//                Car car = new Car(enumCar.getManufacturer(),
//                        enumCar.getName(),
//                        enumCar.getFee(),
//                        city,
//                        enumCar.getCarType());
//                entityRepository.persist(car);
//                cars.add(car);
//            }
//        }
//    }
//
//    private void createRentals() {
//        if (cars != null && cars.size() > 2) {
//        	entityRepository.persist(new Rental(DateUtil.toDate("2011-05-25T15:00:00.000Z"), DateUtil.toDate("2011-05-28T21:30:00.000Z"), cars.get(0), customer));
//        	entityRepository.persist(new Rental(DateUtil.toDate("2011-07-05T09:45:00.000Z"), DateUtil.toDate("2011-07-05T19:15:00.000Z"), cars.get(1), customer));
//        	entityRepository.persist(new Rental(DateUtil.toDate("2011-08-12T07:00:00.000Z"), DateUtil.toDate("2011-09-02T18:00:00.000Z"), cars.get(2), customer));
//        }
//    }

}
