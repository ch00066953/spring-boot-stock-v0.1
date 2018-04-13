package com.ch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfoExample;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassifyStock {
	@Autowired
	StockInfoMapper StockInfoMapper;
	@Setter
	StockInfoExample example;
	
	
}
