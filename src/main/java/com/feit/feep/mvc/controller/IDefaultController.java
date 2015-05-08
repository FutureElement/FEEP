package com.feit.feep.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feit.feep.exception.mvc.FeepControllerException;
import org.springframework.ui.ModelMap;

public interface IDefaultController {

    String link(String resourceName, ModelMap mm, HttpServletRequest request, HttpServletResponse response) throws FeepControllerException;

    Object service(HttpServletRequest request, HttpServletResponse response, String methodName, String parameters) throws FeepControllerException;

    void upload(HttpServletRequest request, HttpServletResponse response) throws FeepControllerException;

    void download(HttpServletRequest request, HttpServletResponse response) throws FeepControllerException;

}
