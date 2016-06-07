package mx.iteso.sergio.emt_ad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class InfoRestrictions extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_restrictions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position){
                case 0:
                    return new Res1();
                case 1:
                    return new Res2();
                case 2:
                    return new Res3();
                case 3:
                    return new Res4();
                case 4:
                    return new Res5();
                case 5:
                    return new Res6();
                case 6:
                    return new Res7();
                default:
                    return new Res1();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "No donar si...";
            /*switch (position) {
                case 0:
                    return "Para donar necesitas";
                case 1:
                    return "Para donar necesitas";
                case 2:
                    return "Para donar necesitas";
                case 3:
                    return "Para donar necesitas";
                case 4:
                    return "Para donar necesitas";
                case 5:
                    return "Para donar necesitas";
            }*/
            //return null;
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

}
