package carrent.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoVeiculo {
    
    private Connection connection;
    
    private int codTV; //Codigo do tipo de veiculo, mantem relacao com a classe Veiculo
    private String tamanho; // tamanho do carro
    private int numPassageiros; // numero passageiros
    private int numPortas; //numero de portas
    private double valorDiarioLocacao; // valor locacao diaria
    private double valorKMRodado; // valor por km rodado
    private double valorFranqNormal; // valor franquia normal
    private double valorFranqReduzida; // valor franquia reudizida
    private boolean ArCondicionado; // 1- Sim / 0- Não 

    public TipoVeiculo(){}
    public TipoVeiculo(int codTV, String tamanho, int numPassageiros, int numPortas, double valorDiarioLocacao, double valorKMROdado, double valorFranqNormal, double valorFranqReduzida, boolean ArCondicionado) {
        this.codTV = codTV;
        this.tamanho = tamanho;
        this.numPassageiros = numPassageiros;
        this.numPortas = numPortas;
        this.valorDiarioLocacao = valorDiarioLocacao;
        this.valorKMRodado = valorKMRodado;
        this.valorFranqNormal = valorFranqNormal;
        this.valorFranqReduzida = valorFranqReduzida;
        this.ArCondicionado = ArCondicionado;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }    
    
public boolean insert(TipoVeiculo tpVeiculo){
        String sql = "INSERT INTO TIPOVEICULO(TAMANHO,NROPASSAGEIROS,NROPORTAS,VALORDIARIOLOCACAO,VALORKMRODADO,VALORFRANQUIANORMAL,VALORFRANQUIAREDUZIDA,ARCONDICIONADO) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, tamanho );
            stmt.setString(2, Integer.toString(numPassageiros));
            stmt.setString(3, Integer.toString(numPortas));
            stmt.setString(4, Double.toString(valorDiarioLocacao));
            stmt.setString(5, Double.toString(valorKMRodado));
            stmt.setString(6, Double.toString(valorFranqNormal));
            stmt.setString(7, Double.toString(valorFranqReduzida));
            stmt.setString(8, Boolean.toString(ArCondicionado));
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("carrent.DAO.TipoVeiculo.insert()");
            return false;
        }
        
    }
    public boolean update(TipoVeiculo tpVeiculo){
        String sql = "UPDATE TIPOVEICULO SET CPF=?,ENDERECO=?,DATANASC=?,SEXO=?,TELEFONEFIXO=?,TELEFONECELULAR=?,NOME=? WHERE CODC="+Integer.toString(codTV);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, tamanho );
            stmt.setString(2, Integer.toString(numPassageiros));
            stmt.setString(3, Integer.toString(numPortas));
            stmt.setString(4, Double.toString(valorDiarioLocacao));
            stmt.setString(5, Double.toString(valorKMRodado));
            stmt.setString(6, Double.toString(valorFranqNormal));
            stmt.setString(7, Double.toString(valorFranqReduzida));
            stmt.setString(8, Boolean.toString(ArCondicionado));
            
            stmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        
    }

    public boolean delete(TipoVeiculo tpVeiculo){
        String sql = "DELETE FROM TIPOVEICULO WHERE CODC = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1,codTV);
            stmt.execute();
            
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public List<TipoVeiculo> listar(){
        String sql = "SELECT * FROM TIPOVEICULO";
        List<TipoVeiculo> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                TipoVeiculo tpVeiculo = new TipoVeiculo();
                tpVeiculo.setCodTV((resultado.getInt("CODTV")));
                tpVeiculo.setTamanho((resultado.getString("TAMANHO")));
                tpVeiculo.setNumPassageiros((resultado.getInt("NROPASSAGEIROS")));
                tpVeiculo.setNumPortas((resultado.getInt("NROPORTAS")));
                tpVeiculo.setValorDiarioLocacao(resultado.getDouble("VALORDIARIOLOCACAO"));
                tpVeiculo.setValorKMROdado(resultado.getDouble("VALORKMRODADO"));
                tpVeiculo.setValorFranqNormal(resultado.getDouble("VALORFRANQUIANORMAL"));
                tpVeiculo.setValorFranqReduzida(resultado.getDouble("VALORFRANQUIAREDUZIDA"));
                tpVeiculo.setArCondicionado(resultado.getBoolean("ARCONDICIONADO"));
                
                retorno.add(tpVeiculo);
            }         
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return retorno;
    }

    public TipoVeiculo select(TipoVeiculo tpVeiculo){
        String sql = "SELECT * FROM TIPOVEICULO WHERE CODC = ?";
        TipoVeiculo rs = new TipoVeiculo();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tpVeiculo.getCodTV());
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
                tpVeiculo.setCodTV((resultado.getInt("CODTV")));
                tpVeiculo.setTamanho((resultado.getString("TAMANHO")));
                tpVeiculo.setNumPassageiros((resultado.getInt("NROPASSAGEIROS")));
                tpVeiculo.setNumPortas((resultado.getInt("NROPORTAS")));
                tpVeiculo.setValorDiarioLocacao(resultado.getDouble("VALORDIARIOLOCACAO"));
                tpVeiculo.setValorKMROdado(resultado.getDouble("VALORKMRODADO"));
                tpVeiculo.setValorFranqNormal(resultado.getDouble("VALORFRANQUIANORMAL"));
                tpVeiculo.setValorFranqReduzida(resultado.getDouble("VALORFRANQUIAREDUZIDA"));
                tpVeiculo.setArCondicionado(resultado.getBoolean("ARCONDICIONADO")); 
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return rs;
    }
    
    public int getCodTV() {
        return codTV;
    }

    public void setCodTV(int codTV) {
        this.codTV = codTV;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public double getValorDiarioLocacao() {
        return valorDiarioLocacao;
    }

    public void setValorDiarioLocacao(double valorDiarioLocacao) {
        this.valorDiarioLocacao = valorDiarioLocacao;
    }

    public double getValorKMROdado() {
        return valorKMRodado;
    }

    public void setValorKMROdado(double valorKMROdado) {
        this.valorKMRodado = valorKMROdado;
    }

    public double getValorFranqNormal() {
        return valorFranqNormal;
    }

    public void setValorFranqNormal(double valorFranqNormal) {
        this.valorFranqNormal = valorFranqNormal;
    }

    public double getValorFranqReduzida() {
        return valorFranqReduzida;
    }

    public void setValorFranqReduzida(double valorFranqReduzida) {
        this.valorFranqReduzida = valorFranqReduzida;
    }

    public boolean getArCondicionado() {
        return ArCondicionado;
    }

    public void setArCondicionado(boolean ArCondicionado) {
        this.ArCondicionado = ArCondicionado;
    }
    
    
}
