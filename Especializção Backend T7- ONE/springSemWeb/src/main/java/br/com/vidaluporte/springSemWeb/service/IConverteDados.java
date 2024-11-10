package br.com.vidaluporte.springSemWeb.service;

public interface IConverteDados {
    <T> T converteDados(String json, Class<T> classe);



}
