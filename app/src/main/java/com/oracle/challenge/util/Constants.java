package com.oracle.challenge.util;

 public interface Constants {

    String TEST = "test";

    int EAST = 0;
    int WEST = 2;
    int NORTH = 3;
    int SOUTH = 1;

    char PLAIN = 'o';
    char ROCKY = 'r';
    char TREE = 't';
    char PROTECTED_TREE = 'T';

    StringBuilder TURN_LEFT = new StringBuilder("turn left, ");
    StringBuilder TURN_RIGHT = new StringBuilder("turn right, ");
    StringBuilder ADVANCE = new StringBuilder("advance ");

}
