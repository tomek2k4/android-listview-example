package test.tomek.com.losowalistaprzyklad;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tomasz on 24.05.2015.
 */
public class Data {

    private List<Charging> backingList = new LinkedList<Charging>();

    private static final Data instance = new Data();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private static Data getInstance(){
        return instance;
    }

    private Data(){

    }


    public void chargingStarted(){
        Lock lock = rwLock.writeLock();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }

    }

    public void chargingStopped(){
        Lock lock = rwLock.writeLock();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }

    public void load(){
        Lock lock = rwLock.writeLock();
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }


    public

}
