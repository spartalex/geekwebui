package lesson4;

public class Box {
    private Integer ballsCounter;

    public Box() {
        this.ballsCounter = 0;
    }

    public Integer getBallsCounter() {
        return ballsCounter;
    }

    public void setBallsCounter(Integer ballsCounter) {
        this.ballsCounter = ballsCounter;
    }

    public void addBall() {
        ballsCounter++;
    }

    public void deleteBall() throws BoxIsEmptyException {
        if (ballsCounter <= 0) throw new BoxIsEmptyException();
        ballsCounter--;
    }

    public void shuffleBalls() {
        if (ballsCounter <= 0) throw new NullPointerException();
    }
}
