package com.otb.githubtracker.common;

/**
 * Created by Mohit Rajput on 06/08/22.
 * Maps one entity of one layer to another entity of different layer
 */
public interface Mapper<E, T> {
    T mapFrom(E from);
}