
package com.Assignment;
 
import java.util.ArrayList;

import java.util.Deque;

import java.util.HashMap;

import java.util.LinkedList;

import java.util.List;

import java.util.Map;
 
public class RecentlyPlayedStore {

    private int capacityPerUser;

    private Map<String, Deque<String>> recentlyPlayed; 

    private Map<String, Integer> playOrder; 
    public RecentlyPlayedStore(int capacityPerUser) {

        this.capacityPerUser = capacityPerUser;

        this.recentlyPlayed = new HashMap<>();

        this.playOrder = new HashMap<>();

    }
 
    public void playSong(String userId, String songId) {

        if (!recentlyPlayed.containsKey(userId)) {

            recentlyPlayed.put(userId, new LinkedList<>());

        }
 
        Deque<String> userPlaylist = recentlyPlayed.get(userId);
 

        if (userPlaylist.contains(songId)) {

            userPlaylist.remove(songId); 

        }

        userPlaylist.addLast(songId);

        playOrder.put(userId + "_" + songId, playOrder.getOrDefault(userId + "_" + songId, 0) + 1);
 
        if (userPlaylist.size() > capacityPerUser) {

            String leastRecentlyPlayed = userPlaylist.removeFirst();

            playOrder.remove(userId + "_" + leastRecentlyPlayed);

        }

    }
 
    public List<String> getRecentlyPlayed(String userId) {

        return new ArrayList<>(recentlyPlayed.getOrDefault(userId, new LinkedList<>()));

    }
 
    public void printStoreState() {

        System.out.println("Recently Played Store State:");

        for (String userId : recentlyPlayed.keySet()) {

            System.out.println("User " + userId + ": " + recentlyPlayed.get(userId));

        }

    }
 
    public static void main(String[] args) {

        RecentlyPlayedStore store = new RecentlyPlayedStore(3);
 
        store.playSong("user1", "S1");

        store.playSong("user1", "S2");

        store.playSong("user1", "S3");

        store.printStoreState();  
 
        store.playSong("user1", "S4");

        store.printStoreState();  
 
        store.playSong("user1", "S2");

        store.printStoreState();  
 
        store.playSong("user1", "S1");

        store.printStoreState();  

    }

}
 
 
