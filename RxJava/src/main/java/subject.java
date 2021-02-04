import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class subject {


    public static void main(String[] args) {
    }
    /**
     * ReplaySubject replays events/items to current and late Observers.
     */

    private static void replaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(System.out::println);
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
        subject.subscribe(System.out::println);
        subject.onNext("d");
        subject.onComplete();
    }


    /**
     * PublishSubject emits items to currently subscribed Observers and terminal events to current or late Observers.
     */
    private static void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(System.out::println );
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
        subject.subscribe(System.out::println);
        subject.onNext("d");
        subject.onComplete();
    }


    /**
     *  This is Subject similar to calling replay(1).autoConnect()
     *  Just emits the last emission to subscriber downstream
     */
    private static void behaviorSubject() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.subscribe(System.out::println);
        subject.onNext(1);
        subject.onNext(10);
        subject.onNext(20);
    }

    /**
     *  Emits only the last element.
     *  Calling onComplete is a must otherwise it wont emit
     */
    private static void asyncSubject() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.subscribe(System.out::println);
        subject.onNext(1);
        subject.onNext(10);
        subject.onNext(20);
        subject.subscribe(System.out::println);
        subject.onComplete();
    }

}
