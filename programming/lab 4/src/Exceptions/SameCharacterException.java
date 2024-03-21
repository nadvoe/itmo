package Exceptions;

public class SameCharacterException extends RuntimeException{
        private String character;
        public SameCharacterException(String message, Throwable cause, String character){
            super(message, cause);
            this.character = character;
        }
        public SameCharacterException(String message){
            super(message);
        }
}

