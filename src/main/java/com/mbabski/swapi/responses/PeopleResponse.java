package com.mbabski.swapi.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeopleResponse extends Response implements Serializable {
    List<PeopleResults> results;
}
