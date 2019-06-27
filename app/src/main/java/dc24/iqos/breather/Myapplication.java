package dc24.iqos.breather;

import android.app.Application;

public class Myapplication extends Application {
    private int mGlobalcount=0;

    public int getGlobal_integer()
    {
        return mGlobalcount;
    }

    public void setGlobal_integer(int globalinteger)
    {
        this.mGlobalcount = globalinteger;
    }

}
