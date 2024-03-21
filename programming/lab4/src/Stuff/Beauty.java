package Stuff;

public enum Beauty {
    STUNNING (" была удивительно красивая. Румяные щеки, голубые глаза и волосы."),
    SHABBY(" была повреждена, краска почти стерлась.");
    private String beauty;
    Beauty (String beauty){
        this.beauty = beauty;
    }
    public String getBeauty(){
        return beauty;
    }
}
