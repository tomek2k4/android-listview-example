package test.tomek.com.losowalistaprzyklad;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import java.text.DateFormat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class RandomListActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private static final int MAXIMUM_DAYS = 901;
    // "data"--> ?, "weekday" ---> ?
    private List<Map<String, String>> data = new LinkedList<Map<String, String>>();
    private SimpleAdapter dataAdapter;
    private int selectedItemFromList = ListView.INVALID_POSITION;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dayOfWeekDateFormat = new SimpleDateFormat("EEEE");

    private String getSelectedItemOfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSimpleDates();

        String[] from = new String[] {"date","weekday"};
        int[] to =new  int[] {android.R.id.text1,android.R.id.text2};

        dataAdapter = new SimpleAdapter(this,data, android.R.layout.simple_list_item_2,from,to);

        getListView().setOnItemClickListener(this);

        setListAdapter(dataAdapter);
        getListView().setSelector(R.drawable.select_shape);
        getListView().setSelected(true);

    }

    private void loadSimpleDates() {

        Map<String, String> record = new HashMap<String, String>();

        Calendar cal = Calendar.getInstance();
        String day = dayOfWeekDateFormat.format(cal.getTime());
        record.put("date", dateFormat.format(cal.getTime()));
        record.put("weekday", day);
        data.add(record);
    }


    private void addRadomDate(){

        Map<String, String> record = new HashMap<String, String>();

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();

        Random rnd = new Random();

        int randomDay = rnd.nextInt(MAXIMUM_DAYS) - MAXIMUM_DAYS/2-1;
        cal.setTime(new Date());
        cal.add(Calendar.DATE, randomDay);

        String day = dayOfWeekDateFormat.format(cal.getTime());
        record.put("date",dateFormat.format(cal.getTime()));
        record.put("weekday",  day);
        data.add(record);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_add){
            addRadomDate();
            dataAdapter.notifyDataSetChanged();
        }else if(id == R.id.action_remove){
            removeDateFromList(selectedItemFromList);
            dataAdapter.notifyDataSetChanged();
        }else if(id == R.id.action_clear){

        }

        return super.onOptionsItemSelected(item);
    }

    private void removeDateFromList(int item) {
        Log.d("Tomek","Item selected: " + new Integer(item).toString());
        try{
            data.remove(item);
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(this,"Index out of bound",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Toast.makeText(this,"Item selected: "+new Integer(pos).toString(),Toast.LENGTH_LONG).show();

        selectedItemFromList = pos; // here you will get selected item position.
    }

}
