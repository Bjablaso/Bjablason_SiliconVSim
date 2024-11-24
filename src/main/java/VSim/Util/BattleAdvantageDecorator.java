package VSim.Util;

import VSim.Core.StartUp;
import VSim.Types.StartupType;

public class BattleAdvantageDecorator extends StartUp {
    private StartUp baseStartup;

    public BattleAdvantageDecorator(StartUp baseStartup) {
        super(
                baseStartup.getType(),
                baseStartup.getNetIncome(),
                baseStartup.getRevenue(),
                baseStartup.getMarketShare(),
                baseStartup.getFunds()
        );

        this.baseStartup = baseStartup;
    }

    public int calculateDamageWithAdvantage(StartUp target){

        return 0;
    }

    private boolean hasTypeAdvantage(StartUp target){
        return false;
    }

    public boolean applyCriticalOrMiss(){
        return false;
    }
}
