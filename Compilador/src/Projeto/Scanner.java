package Projeto;
import java.io.FileReader;
import java.io.IOException;

/*
 * Análise léxica é o processo de analisar a entrada de linhas de caracteres (tal como o código-fonte de um programa de computador) e produzir uma seqüência de símbolos chamado "símbolos léxicos" (lexical tokens), ou somente "símbolos" (tokens),
 * que podem ser manipulados mais facilmente por um parser (leitor de saída).
 */

	public class Scanner {

	    public static final char EOF = '\uffff';//é uma parada padrão que indica fim do arquivo // VÊ SE É O FINAL DO ARQUIVO
	    private FileReader fr; //criar arquivo 
	    public static int linha = 1;
	    public static int coluna = 0;
	    public static char la = ' '; //string do arquivo cmd cada caracter é o lexema

	    public Scanner(FileReader fr) {
	        setFileReader(fr);
	    }

	    public void setFileReader(FileReader fr) {
	        this.fr = fr;
	    }

	    private void ler() throws IOException {
	        try {
	            la = (char) this.fr.read();

	            if (la == '\n') {
	                coluna = 0;
	                linha++;
	            } else if (la == '\t') {//tab
	                coluna = coluna + 4;
	            } else {
	                coluna++;
	            }
	        } catch (IOException ex) {
	            throw new IOException();
	        }
	    }
	    
	    /*
	    IOException ()
	    Constrói um IOException com nulo como sua mensagem de detalhe erro.
	    IOException ( Cadeia  mensagem)
	    Constrói um IOException com a mensagem de detalhe especificada.
	    IOException ( Cadeia  mensagem, Throwable  causa)
	    Constrói um IOException com a mensagem de detalhe especificada e causa.
	    IOException ( Throwable  cause)
	    Constrói um IOException com a causa especificada e uma mensagem de detalhe 
	    (cause == null null:? cause.toString ()) (que normalmente contém a mensagem de classe e os detalhes da causa ).
*/
	    public Token scanner() throws ExceptionLinhasCarac, IOException {

	        try {
	            String lexema = ""; //novo atributo

	            while (la == ' ' || la == '\r' || la == '\n' || la == '\t') {
	                ler();
	            }
	            
	            if (la == EOF) {
	               return new Token("", Token.EOF); //retorna -1
	            }

	            lexema += la;//pegando cada caracter para forma um token

	            if (Character.isLetter(la) || la == '_') //é para ver se é char{         

	                ler();

	                while (Character.isLetterOrDigit(la) || la == '_') {       
	                    lexema += la;
	                    ler();
	                }
	                
	                switch (lexema) {
	                    case "main":
	                        return new Token(lexema, Token.MAIN);
	                    case "if":
	                        return new Token(lexema, Token.IF);
	                    case "else":
	                        return new Token(lexema, Token.ELSE);
	                    case "do":
	                        return new Token(lexema, Token.DO);
	                    case "while":
	                        return new Token(lexema, Token.WHILE);
                        case "for":
	                        return new Token(lexema, Token.FOR);
	                    case "int":
	                        return new Token(lexema, Token.INT);
	                    case "float":
	                        return new Token(lexema, Token.FLOAT);
	                    case "char":
	                        return new Token(lexema, Token.CHAR);
	                    default:
	                        return new Token(lexema, Token.ID);
	                }
	                
	            } else if (la == '<') {
	                ler();
	                if (la == '=') {
	                    lexema += la;
	                    ler();
	                    return new Token(lexema, Token.MENOR_OU_IGUAL);
	                } else {
	                    return new Token(lexema, Token.MENOR);
	                }
	            } else if (la == '>') {
	                ler();
	                if (la == '=') {
	                    lexema += la;
	                    ler();
	                    return new Token(lexema, Token.MAIOR_OU_IGUAL);
	                } else {
	                    return new Token(lexema, Token.MAIOR);
	                }
	            } else if (la == '=') {
	                ler();
	                if (la == '=') {
	                    lexema += la;
	                    ler();
	                    return new Token(lexema, Token.IGUAL);
	                } else {
	                    return new Token(lexema, Token.ATRIBUICAO);
	                }
	            } else if (la == '+') {                                    
	                ler();
	                return new Token(lexema, Token.SOMA);
	            } else if (la == '-') {                               
	                ler();
	                return new Token(lexema, Token.SUB);
	            } else if (la == '*') {                                  
	                ler();
	                return new Token(lexema, Token.MULT);
	            } else if (la == '(') {
	                ler();
	                return new Token(lexema, Token.ABRE_PARENTESES);
	            } else if (la == ')') {
	                ler();
	                return new Token(lexema, Token.FECHA_PARENTESES);
	            } else if (la == '{') {
	                ler();
	                return new Token(lexema, Token.ABRE_CHAVES);
	            } else if (la == '}') {
	                ler();
	                return new Token(lexema, Token.FECHA_CHAVES);
	            } else if (la == ',') {
	                ler();
	                return new Token(lexema, Token.VIRGULA);
	            } else if (la == ';') {
	                ler();
	                return new Token(lexema, Token.PONTO_VIRGULA);
	            } else if (la == '!') {                                  
	                ler();
	                if (la != '=') {  
	                    throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                            + "\tultimo token: " + lexema + "\tDiferença mal formada");
	                }
	                lexema += la;
	                ler();
	                return new Token(lexema, Token.DIFERENTE);
	            } else if (la == '\'') {                                 
	                ler();
	                if (!Character.isLetterOrDigit(la)) {                      
	                    throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                            + "\tultimo token: " + lexema + "\tChar mal formado");
	                }
	                lexema += la;
	                ler();
	                if (la != '\'') {
	                    throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                           + "\tultimo token: " + lexema + "\tChar mal formado");
	                }
	                lexema += la;
	                ler();
	                return new Token(lexema, Token.TIPO_CHAR);
	            } else if (Character.isDigit(la)) {                      
	                ler();
	                if (la == '.') {                              
	                    lexema += la;
	                    ler();
	                    if (!Character.isDigit(la)) {   
	                        throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                                + "\tultimo token: " + lexema + "\tFloat mal formado");
	                    }
	                    while (Character.isDigit(la)) {
	                         lexema += la;
	                         ler();
	                    }
	                        return new Token(lexema, Token.TIPO_FLOAT); 
	                } else if (Character.isDigit(la)) {
	                    lexema += la;
	                    ler();
	                    while (Character.isDigit(la)) {        
	                        lexema += la;
	                        ler();
	                    }
	                }
	                return new Token(lexema, Token.TIPO_INT);
	            } else if (la == '.') {                                                  
	                ler();
	                if (!Character.isDigit(la)) {   
	                    throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                        + "\tultimo token: " + lexema + "\tFloat mal formado");
	                }
	                while (Character.isDigit(la)) {
	                      lexema += la;
	                      ler();
	                }
	                return new Token(lexema, Token.TIPO_FLOAT); 
	            } else if (la == '/') {
	                ler();
	                if (la == '/') {
	                    while (la != '\n') {
	                        ler();
	                    }
	                    return scanner();
	                } else if (la == '*') {
	                    while (true) {
	                        boolean flag = false;
	                        ler();
	                        while (la == '*') {
	                            ler();
	                            flag = true;
	                            if (la == Scanner.EOF) {
	                                throw new ExceptionLinhasCarac("Erro Fim de arquivo no comentario");
	                            }
	                        }
	                        if (la == '/' && flag) {
	                            ler();
	                            return scanner();
	                        } else if (la == Scanner.EOF) {
	                            throw new ExceptionLinhasCarac("Erro Fim de arquivo no comentario");
	                        }
	                    }
	                } else {
	                    return new Token(lexema, Token.DIVISAO);
	                }
	            } 
	        } catch (IOException ex) {
	            throw new IOException();
	        }
	        throw new ExceptionLinhasCarac("Erro linha: " + linha + "\tcoluna: " + coluna
	                    + "\tCaracter Invalido!");
	    }
	}

