package vsim.util;

import vsim.core.StartUp;
import vsim.types.AttackType;
import vsim.types.StartupType;


public class BattleAdvantageDecorator extends StartUp {
    private boolean isAdvantage;
    private final  double attackBoost = 10.0;

    public BattleAdvantageDecorator(StartUp baseStartup) {
        super(
                baseStartup.getName(),
                baseStartup.getType(),
                baseStartup.getNetIncome(),
                baseStartup.getRevenue(),
                baseStartup.getMarketShare(),
                baseStartup.getGrade(),
               // baseStartup.getFunds(),
                baseStartup.getLevel()
        );


        this.isAdvantage = false;

    }


    /**
     * Determine if StartUp has an Advantage
     * @param target
     * @return true for isAdvantage if this start up has an advantage base on the unique scenario below
     */
    public BattleAdvantageDecorator determinAdvantage(StartUp target){
        if(getType().equals(StartupType.OPERATING_SYSTEMS) && target.getType().equals(StartupType.SOCIAL_MEDIA)){
            this.isAdvantage = true;
            System.out.println(this.getName() + " is given a % "+ attackBoost + " attack boost as advantage over " + target.getName());
            return this;
        } else if (getType().equals(StartupType.FINTECH) && target.getType().equals(StartupType.REAL_ESTATE)) {
            this.isAdvantage = true;
            System.out.println(this.getName() + " is given a % "+ attackBoost + " attack boost as advantage over " + target.getName());
            return this;
        }

        return this;
    }

    /**
     * Apply a %10 percent increase to all attack for  this  startup with
     * @return
     */
    public BattleAdvantageDecorator calculateDamageWithAdvantage(){
      //  List<AttackType> potentialAttacks = baseStartup.getAttacks();
          for(AttackType currentAttack : this.getAttacks()){
              double damage =  currentAttack.getDamage() + ( currentAttack.getDamage() * (attackBoost/100));
              currentAttack.setDamage(currentAttack, damage);
          }
         // this.setAttacks(potentialAttacks);
        return this;
    }

    public boolean applyCriticalOrMiss(){
        return false;
    }


    public boolean isAdvantage() { return isAdvantage; }
}
