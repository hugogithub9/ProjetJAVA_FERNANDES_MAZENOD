import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<DynamicSprite2> movingSpriteList2;
    private ArrayList <Sprite> environment;

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        movingSpriteList2 = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    public void addToMovingSpriteList2(DynamicSprite2 sprite){
        if (!movingSpriteList2.contains(sprite)){
            movingSpriteList2.add(sprite);
        }
    }

    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);
        }

        for(DynamicSprite2 dynamicSprite : movingSpriteList2){
            dynamicSprite.moveIfPossible(environment);
        }
    }


}
