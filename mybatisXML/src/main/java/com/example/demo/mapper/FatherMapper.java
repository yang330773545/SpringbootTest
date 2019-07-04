package com.example.demo.mapper;

import com.example.demo.entity.Father;

public interface FatherMapper {
	Father findFatherSonByFatherId(int fatherId);
}
