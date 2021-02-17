package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.UserG;
import com.example.demo.domain.valinterface.annotation.LDValidations;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.reflections.Reflections;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

@Slf4j
@Service
public class StudentService {

	@Cacheable("student")
	public Student getStudentByID(String id) {
		log.info("entering");
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Student(id,"Sajal" ,"V");
		
	}

	@Cacheable("classes")
	public Set<Class<?>> getClasses() {

		log.info("getting....");
		return new Reflections("com.example.demo.domain.valinterface")
				.getTypesAnnotatedWith(LDValidations.class);
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		log.info(TimeZone.getDefault().getDisplayName());
	}

	@Scheduled(cron = "* * * * * ?")
	public void crontest() {
		log.info("The time is now cron {}", dateFormat.format(new Date()));
		log.info(TimeZone.getDefault().getDisplayName());
	}

	@Scheduled(cron = "* * * * * ?")
	public void retrofit() {

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(httpClient.build())
				.build();

		UserService service = retrofit.create(UserService.class);
		Call<UserG> callSync = service.getUser("eugenp");

		try {
			Response<UserG> response = callSync.execute();
			UserG user = response.body();
			log.info("USER: {}", user);
		} catch (Exception ex) {
			log.error("error: ", ex);
		}

		Call<UserG> callAsync = service.getUser("vzla0212");

		callAsync.enqueue(new Callback<UserG>() {
			@Override
			public void onResponse(Call<UserG> call, Response<UserG> response) {
				UserG user = response.body();
				log.info("USER ASYNC: {}", user);
			}

			@Override
			public void onFailure(Call<UserG> call, Throwable throwable) {
				log.error("error: ", throwable);
			}
		});
	}

}
