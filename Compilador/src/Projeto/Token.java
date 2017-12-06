package Projeto;
//Seria o Token, lembrando que  é um segmento de texto ou símbolo que pode ser manipulado por um analisador sintáctico, que fornece um significado ao texto; em outras palavras, é um conjunto de caracteres
//(de um alfabeto, por exemplo)nesse caso a linguagem do professor, com um significado coletivo.

public class Token {
	
	private int linha;
    private int coluna;
    private int codigo;
    private String lexema;
    
    public static final int MAIN = 0;
    public static final int ID = 1;
    public static final int FLOAT = 2;
    public static final int CHAR = 3;
    public static final int INT = 4;
    public static final int DO = 5;
    public static final int WHILE = 6;
    public static final int FOR = 7;
    public static final int IF = 8;
    public static final int ELSE = 9;
    //Comandos Básicos-Palavra Reservada = main x |  if x |  else x |  while x |  do x |  for x |  int x |  float x |  char x
    
    public static final int TIPO_FLOAT = 11;
    public static final int TIPO_CHAR = 10;
    public static final int TIPO_INT = 12;
    public static final int DIFERENTE = 21;
    public static final int IGUAL = 22;
    public static final int MAIOR_OU_IGUAL = 14;
    public static final int MENOR_OU_IGUAL = 16;
    public static final int MAIOR = 13;
    public static final int MENOR = 15;
    //Operacional ::= <  |  >  |  <=  |  >=  |  ==  |  !=
    
    public static final int SOMA = 17;
    public static final int SUB = 18;
    public static final int MULT = 19;
    public static final int DIVISAO = 20;
    //Operações ::= "+" x |  "-" x |  "*" x  |  "/" x |  "=" x
    
    public static final int ABRE_PARENTESES = 23;
    public static final int FECHA_PARENTESES = 24;
    public static final int ABRE_CHAVES = 25;
    public static final int FECHA_CHAVES = 26;
    public static final int VIRGULA = 27;
    public static final int PONTO_VIRGULA = 28;
    public static final int ATRIBUICAO = 29;
    public static final int EOF = 30;
   // especial := ")" x |  "(" x |  "{" x  |  "}" x |  "," x |  ";" x
    
    
    
    public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public Token(){
    }
    
	public Token(String lexema, int codigo) {
		this.lexema = lexema;
        this.codigo = codigo;
        
        
    }

 
    
    @Override
    public String toString(){
        return "codigo: " + this.codigo + "\tlexema: " + this.lexema + "\tlinha: " +
                this.linha + "\tcoluna: " + this.coluna;
    }
}

