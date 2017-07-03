package kritika.in.collegeapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kritika.in.collegeapp.R;
import kritika.in.collegeapp.subjects;
import kritika.in.collegeapp.ui.adapter.MyTimeTableAdapter;
import kritika.in.collegeapp.utils.CollegeAppPreference;

public class HomePageActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private MyHomePageViewPagerAdapter myHomePageViewPagerAdapter;
    private ImageView headerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_homepage);
        setSupportActionBar(toolbar);
        String message;
        message = CollegeAppPreference.getUSERNAME();
        myHomePageViewPagerAdapter = new MyHomePageViewPagerAdapter(getSupportFragmentManager());


        viewPager = (ViewPager) findViewById(R.id.homepage_viewpager);
        viewPager.setAdapter(myHomePageViewPagerAdapter);
        headerImage=(ImageView) findViewById(R.id.backdrop);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.homepage_tablayout);
        tabLayout.setupWithViewPager(viewPager);

        TextView rollno = (TextView) findViewById(R.id.roll_no_txt);
        rollno.setText(message);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        ImageView navheader_img= (ImageView) headerView.findViewById(R.id.imageView_navheader);
        navheader_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ConnectStoriesActivity.class);
                startActivity(i);
            }
        });

        initCollapsingToolbar();
    }

    private void setImage(int imageId){

        try {
            Glide.with(this).load(imageId).into(headerImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.action_settings) {
             Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
             startActivity(i);
            return true;
        } else if (id == R.id.btn_logout) {
            logout();
            return true;
        }else if (id == R.id.btn_aboutus) {
            Intent i =new Intent(getApplicationContext(),AboutUsActivity.class);
             startActivity(i);
             return true;
         }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();

        if (id == R.id.nav_attendance) {
            intent = new Intent(getApplicationContext(), AttendanceActivity.class);
        } else if (id == R.id.nav_annapurna) {
            intent = new Intent(getApplicationContext(), AnnapurnaActivity.class);

        } else if (id == R.id.nav_exam) {
            intent = new Intent(getApplicationContext(), ExamScheduleActivity.class);

        } else if (id == R.id.nav_cgpacal) {
            intent = new Intent(getApplicationContext(), CGPAMainActivity.class);

        } else if (id == R.id.nav_placement) {

        } else if (id == R.id.nav_webview) {
        }

        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_homepage);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    public static class MyHomePageFragment extends Fragment {
        private RecyclerView rv;
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager lm;
        private static String LOG_TAG = "Homepage";
        Context mcontext;
        private static final String ARG_SECTION_NUMBER = "image_no:";
        int imageId=0;


        public MyHomePageFragment() {}

        public static MyHomePageFragment newInstance(int sectionNumber) {
            MyHomePageFragment fragment = new MyHomePageFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_homepage_viewpager, container, false);

         //   ImageView imageView= (ImageView) view.findViewById(R.id.homepage_viewpager_img);

            int imgId=getArguments().getInt(ARG_SECTION_NUMBER);
         //   imageView.setImageResource(imgId);

            rv = (RecyclerView) view.findViewById(R.id.recyclerview_timetablepage);
            rv.setHasFixedSize(true);
            lm = new LinearLayoutManager(mcontext);
            rv.setLayoutManager(lm);
            adapter = new MyTimeTableAdapter(getDataSet());

            ImageView image = (ImageView) view.findViewById(R.id.homepage_viewpager_img);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.campus_one);
               // image.setImageResource(R.mipmap.campus_one);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.campus_two);
              //  image.setImageResource(R.mipmap.campus_two);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3)
            {
                /*rv.setAdapter(adapter);*/
                ((HomePageActivity)mcontext).setImage(R.mipmap.campus_three);
             //   image.setImageResource(R.mipmap.campus_three);
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.wakh_one);
              //  image.setImageResource(R.mipmap.wakh_one);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==5)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.wakh_two);
               // image.setImageResource(R.mipmap.wakh_two);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==6)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.wakh_three);
                //image.setImageResource(R.mipmap.wakh_three);
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==7)
            {
                ((HomePageActivity)mcontext).setImage(R.mipmap.jiit_one);
              //  image.setImageResource(R.mipmap.jiit_one);
            }
          rv.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mcontext, LinearLayoutManager.VERTICAL);
            rv.addItemDecoration(itemDecoration);
            return view;
        }

        public void onResume() {
            super.onResume();
            ((MyTimeTableAdapter) adapter).setOnItemClickListener(new
                                                                          MyTimeTableAdapter.MyClickListener() {
                                                                              @Override
                                                                              public void onItemClick(int position, View v) {
                                                                                  Log.i(LOG_TAG, " Clicked on Item " + position);
                                                                              }
                                                                          });
        }

        public ArrayList<subjects> getDataSet() {
            ArrayList results = new ArrayList<subjects>();

            subjects obj = null;
            results.add(new subjects("JAVA"));
            results.add(new subjects("C++"));
            results.add(new subjects("Android"));
            results.add(new subjects("Big data"));
            results.add(new subjects("Html"));
            results.add(new subjects("PHP"));
            results.add(new subjects("French"));
            return results;

        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mcontext = context;
        }
    }

    class MyHomePageViewPagerAdapter extends FragmentStatePagerAdapter {
        public MyHomePageViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyHomePageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MONDAY";
                case 1:
                    return "TUESDAY";
                case 2:
                    return "WEDNESDAY";
                case 3:
                    return "THURSDAY";
                case 4:
                    return "FRIDAY";
                case 5:
                    return "SATURDAY";
                case 6:
                    return "SUNDAY";

            }
            return null;
        }

    }


}

