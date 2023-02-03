public class SAAssign {
    public static void main(String[] args) {
//        Bus bus = new Bus(1);
//        System.out.println("------승객2명 탑승------");
//        bus.setPassenger(2);
//        System.out.println("------주유량 -50------");
//        bus.setCurrentOil(-50);
//        System.out.println("------상태변경 차고지행------");
//        bus.setState("차고지행");
//        System.out.println("------주유량 + 10------");
//        bus.setCurrentOil(10);
//        System.out.println("------상태변경 운행------");
//        bus.setState("운행");
//        System.out.println("------승객 45명 탑승------");
//        bus.setPassenger(45);
//        System.out.println("------승객5명 탑승------");
//        bus.setPassenger(5);
//        System.out.println("------주유량 -55------");
//        bus.setCurrentOil(-55);


        Taxi taxi = new Taxi(1);
        System.out.println("------승객2명 탑승------");
        taxi.setPassenger(2);
        System.out.println("------목적지 서울역 거리 2km 입력------");
        taxi.setDestination("서울역", 2);
        System.out.println("------주유량 -80------");
        taxi.setCurrentOil(-80);
        System.out.println("------승객5명 탑승------");
        taxi.setPassenger(5);
        System.out.println("------승객3명 탑승------");
        taxi.setPassenger(3);
        System.out.println("------목적지 구로디지털단지역 거리 12km 입력------");
        taxi.setDestination("구로디지털단지역", 12);
        System.out.println("-------주유량 -20------");
        taxi.setCurrentOil(-20);
    }

}

class Transport {
    private int carNum;
    private int currentOil=100;
    private int currentSpeed=0;
    private String  state;
    private int currentPeopleNum;

    Transport(int n) {
        setCarNum(n);
    }

    void setPassenger(int people) {
        currentPeopleNum += people;
    }

    public int getCarNum() {
        return carNum;
    }
    public int getCurrentOil() {
        return currentOil;
    }
    public int getCurrentSpeed() {
        return currentSpeed;
    }
    public String getState() {
        return state;
    }
    public int getCurrentPeopleNum() {
        return currentPeopleNum;
    }
    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }
    public void setCurrentOil(int currentOil) {
        this.currentOil += currentOil;
    }
    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed += currentSpeed;
        System.out.println("현재 속도는 "+this.currentSpeed+"입니다.");
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setCurrentPeopleNum(int currentPeopleNum) {
        this.currentPeopleNum = currentPeopleNum;
    }
}
class Bus extends Transport{
    private final int busPrice =1000;
    Bus(int n) {
        super(n);
        super.setState("운행");
        System.out.println(n+"번 버스객체 만들어짐!");
    }

    @Override
    void setPassenger(int people) {
        if (people + getCurrentPeopleNum() > 30) {
            System.out.println("최대 승객 수를 초과했습니다.");
        } else if (!getState().equals("운행")) {
            System.out.println("운행중이 아닙니다.");
        } else {
            super.setPassenger(people);
            System.out.println("탑승 승객 수 = " + people);
            System.out.println("잔여 승객 수 = " + (30 - getCurrentPeopleNum()));
            System.out.println("요금 확인 = " + (busPrice * people));
        }
    }

    @Override
    public void setCurrentOil(int currentOil) {
        super.setCurrentOil(currentOil);
        if (getCurrentOil() >= 10) {
            if (getState().equals("운행")) {
                System.out.println("주유량 = " +super.getCurrentOil());
            } else if (getState().equals("차고지행")) {
                System.out.println("상태 = " +getState());
                System.out.println("주유량 = " +super.getCurrentOil());
            }
        } else {
            System.out.println("주유량 = " + getCurrentOil());
            System.out.println("주유가 필요합니다.");
            setState("차고지행");
            System.out.println("상태 = " + getState());
        }
    }

//    public void setCurrentOilCurrentState(int currentOil, String state) {
//        setCurrentOil(currentOil);
//        setState(state);
//        System.out.println("상태 = "+getState());
//        System.out.println("주유량 = " +getCurrentOil());
//    }

    @Override
    public void setState(String state) {
        super.setState(state);
    }
}
class Taxi extends Transport{
    private String destination;
    private final int basePrice = 3000;
    private final int addPrice = 1000;
    private int accumulatePrice;

    Taxi(int n) {
        super(n);
        super.setState("일반");
        System.out.println(n+"번 택시 생성");
        System.out.println("주유량 : "+getCurrentOil());
        System.out.println("상태 : "+getState());
    }

    @Override
    void setPassenger(int people) {
        if (getCurrentPeopleNum()+people > 4) {
            System.out.println("최대 승객 수 초과");
        } else if (people == 0) {
            super.setPassenger(people);
        } else {
            super.setPassenger(people);
            System.out.println("탑승 승객 수 = " + getCurrentPeopleNum());
            System.out.println("잔여 승객 수 = " + (4 - getCurrentPeopleNum()));
        }
    }

    public void setDestination(String destination, int distance) {
        this.destination = destination;
        System.out.println("기본 요금 확인 = "+basePrice);
        System.out.println("목적지 = " +this.destination);
        System.out.println("목적지까지 거리 = " +distance);
        System.out.println("지불할 요금 = "+(basePrice+(distance-1)*addPrice));
        setState("운행중");
        System.out.println("상태 = "+getState());
        accumulatePrice+=(basePrice+(distance-1)*addPrice);
    }

    @Override
    public void setCurrentOil(int currentOil) {
        super.setCurrentOil(currentOil);
        System.out.println("주유량 = "+getCurrentOil());
        System.out.println("누적 요금 = " +accumulatePrice);
        setPassenger(-getCurrentPeopleNum());
        if (getCurrentOil() < 10) {
            System.out.println("주유 필요");
            setState("운행불가");
            System.out.println("상태 = "+getState());
        }

    }
}
