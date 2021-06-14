package com.company;

public class Pontos {
    // atributos da classe Pontos
    private String data, nome, telefone, logradouro, latitude, longitude;
    private int codigo, numero;
    private double distancia;

    // método construtor da classe
    public Pontos(String data, int codigo, String nome, String telefone, String logradouro, int numero, String latitude,
            String longitude, double distancia) {
        this.data = data;
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numero = numero;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distancia = distancia;
    }

    // métodos getters and setters da classe
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(Double newdistancia) {
        this.distancia = newdistancia;
    }

    // método toString() da classe
    @Override
    public String toString() {
        return "Pontos [codigo=" + codigo + ", data=" + data + ", latitude=" + latitude + ", logradouro=" + logradouro
                + ", longitude=" + longitude + ", nome=" + nome + ", numero=" + numero + ", telefone=" + telefone
                + ", distancia=" + distancia + "]";
    }

    // método que retorne em double a distância entre duas coordenadas geográficas
    public double Haversine(double lat1, double long1, double lat2, double long2) {
        double R = 6372.8; // em km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(long2 - long1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
