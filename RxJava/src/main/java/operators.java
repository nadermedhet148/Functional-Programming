import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class operators {
    public static void main(String[] args) throws InterruptedException {

  
    }

    private static void conncat() {
        /**
         * It is very similar to merging operators
         * but here,the it emits the elements in the sequential order
         * of sources add
         */
        @NonNull Observable<String> obsA = Observable.just("itemA");
        @NonNull Observable<String> obsB = Observable.just("itemB");
        Observable.concat(obsA , obsB).subscribe(System.out::println);
    }

    private static void refactor() {
        /**
         *  Groups observables based on a specified key
         *  in to separate Observables
         */
        Observable.range(1, 100).groupBy(ele->ele%2==0 ? 'E' : 'F').flatMapSingle(Observable::toList).subscribe(System.out::println);
    }

    private static void replay() throws InterruptedException {
        /**
         * Caches the emissions & Re-emits to the next observer
         */

        @NonNull Observable<Long> replayObs = Observable.interval(1, TimeUnit.SECONDS).map(ele -> ele + 1).replay(2).autoConnect();

        replayObs.subscribe(System.out::println);
        Thread.sleep(3 * 1000);
        replayObs.subscribe(System.out::println);
        Thread.sleep(3 * 1000);
    }

    private static void zip() {
    /*Take emission from each source,combine them in to one!
    When one of them sends an onComplete , it stops zipping.
    The emissions of others which are yet to emit are dropped as they have nothing to be paired with*/
        @NonNull Observable<Integer> obsA = Observable.range(1, 100);
        @NonNull Observable<Integer> obsB = Observable.range(100, 200);
        @NonNull Observable<Integer> obsC = Observable.range(200, 300);
        Observable.zip(obsA,obsB,obsC , (eleA , eleB , eleC)-> (eleA * eleB) + eleC).subscribe(System.out::println);
    }

    private static void opratores() {
        //:Similar to scan,but adds up all the values before emitting a single
        Observable.range(1 , 100).reduce((total , next)-> total + next ).subscribe(System.out::println);

        //take:Takes the number of emissions
        Observable.fromArray(1,3,5).take(2).subscribe(System.out::println);


        //Skip:Opposite of take(),ignores specified number of emissions
        Observable.fromArray(1,3,5).skip(2).subscribe(System.out::println);


        //distinct() : Skips all the duplicate emissions
        Observable.fromArray(1,3,4,5,6,5).distinct().subscribe(System.out::println);

        //map:transforms from one form to other
        Observable.fromArray(1,3,4,5,6,5).map(ele->ele * 100 ).subscribe(System.out::println);

        //scan():Rolling aggregator.Can also provide initial value in one of the flavors
        Observable.fromArray(1,3,4,5,6,5).scan((itemA , itemB) -> itemA + itemB).subscribe(System.out::println);
    }

    private static void conditions() {
        //takeWhile
        Observable.range(1, 100)
        .takeWhile(ele -> ele < 50).subscribe(System.out::println);

        //skipWhile
        Observable.range(1, 100)
                .skipWhile(ele -> ele < 50).subscribe(System.out::println);

        //filter
        Observable.range(1, 100)
                .filter(ele -> ele % 2 ==0).subscribe(System.out::println);
    }
}
