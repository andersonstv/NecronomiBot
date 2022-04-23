package io.github.andersonstv.character;

public interface Character {
    String getId();
    String check(String stat);
    String check(String stat, String secondaryStat);
    void setStat(String statName, int value);
}
