import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class observable {

    public static void main(String[] args) {
        ConnectableObserve();

    }

    private static void ConnectableObserve() {
        ConnectableObservable firstMillion = Observable.
                range(1, 1000000).
                sample(7, java.util.concurrent.TimeUnit.MILLISECONDS).publish();

        firstMillion.
                subscribe(next -> System.out.println("Subscriber #1: " + next),
                throwable -> System.out.println("Error: " + throwable),
                () -> System.out.println("Sequence #1 complete")
        );

        firstMillion.
                subscribe(next -> System.out.println("Subscriber #2: " + next),
                throwable -> System.out.println("Error: " + throwable),
                () -> System.out.println("Sequence #2 complete")
        );
        firstMillion.connect();
    }

    private static void mapStrings() {
        @NonNull Observable<String> myObservable = Observable.fromArray("item21", "item223", "item3");
        myObservable.map(String::length).
                filter(integer -> integer > 6).
                forEach(System.out::println);
    }
}
