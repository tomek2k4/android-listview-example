package test.tomek.com.losowalistaprzyklad;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tomasz on 24.05.2015.
 */
public class Data {

    private Deque<Charging> backingList = new LinkedList<Charging>();

    private boolean isLoaded = false;
    private ReentrantLock lock = new ReentrantLock();

    private static final Data instance = new Data();

    private static Data getInstance(){
        return instance;
    }

    private Data(){

    }

    public void chargingStarted() {
        lock.lock();
        try {
            load();
            if (backingList.isEmpty()) {
                Charging nc = new Charging();
                backingList.addLast(nc);
            } else {
                Charging last = backingList.getLast();
                if (last.getStop() != null) {
                    Charging nc = new Charging();
                    backingList.addLast(nc);
                } else {
                    last.setStartAsNow();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void chargingStopped() {
        lock.lock();
        try {
            load();
            if (!backingList.isEmpty()) {
                Charging last = backingList.getLast();
                if (last.getStop() == null) {
                    last.setStopAsNow();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void load() {
        if (!isLoaded) {
            // TODO: Load the data
            isLoaded = true;
        }
    }

    public Charging[] getChargingArray() {
        lock.lock();
        try {
            load();
            return backingList.toArray(new Charging[0]);
        } finally {
            lock.unlock();
        }
    }

}
