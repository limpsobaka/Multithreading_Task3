import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class MultiThreadSummary extends RecursiveTask<Integer> {

  private int[] array;

  public MultiThreadSummary(int[] array) {
    this.array = array;
  }

  @Override
  protected Integer compute() {
    if (array.length <= 2) {
      return Arrays.stream(array).sum();
    }
    MultiThreadSummary firstHalfArrayMultiThreadSummary = new MultiThreadSummary(Arrays.copyOfRange(array, 0, array.length / 2));
    MultiThreadSummary secondHalfArrayMultiThreadSummary = new MultiThreadSummary(Arrays.copyOfRange(array, array.length / 2, array.length));
    firstHalfArrayMultiThreadSummary.fork();
    secondHalfArrayMultiThreadSummary.fork();
    return firstHalfArrayMultiThreadSummary.join() + secondHalfArrayMultiThreadSummary.join();
  }
}