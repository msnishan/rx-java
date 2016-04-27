package com.msnishan.rx;

import com.msnishan.io.MyFileReader;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Nishan on 4/24/2016.
 */
public class RxMain {

    public static void main(String[] args) {
        System.out.println();
        List<Integer> intList = Arrays.asList(10, 2, 23, 111, 32, 111, 3, 5, 9, 3, 5, 230, 5, 7);
        intList.forEach(System.out::println);

        Observable<String>  myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello World");
                        subscriber.onCompleted();
                    }
                }
        );



        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Bye :-)");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);

        Observable.just("Hello to next")
                  .subscribe(new Action1<String>() {
                      @Override
                      public void call(String s) {
                          System.out.println(s);
                      }
                  });

        Observable.just("Using Lambda")
                  .subscribe(a -> System.out.println(a));

        Observable.just("Using Lambda Map")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "Nishan";
                    }
                })
                .subscribe(System.out::println);


        Observable.just("Using Lambda Map2")
                .map(s -> s + "Nishan")
                .subscribe(System.out::println);


        MyFileReader myFileReader = new MyFileReader("D:\\GIT\\java-learn-anything\\src\\main\\resources\\students.txt");

        List<String> names = myFileReader.getStudentNames();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                names.forEach(s -> {subscriber.onNext(s);
                    try {
                        SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }).subscribe(System.out::println);


        Stream.iterate(1, n -> n + 10)
                .limit(100)
                .forEach(System.out::println);

    }




}
