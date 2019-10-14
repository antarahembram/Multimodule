package com.stackroute.springbootzuulgatewayproxy;

import com.stackroute.springbootzuulgatewayproxy.filter.ErrorFilter;
import com.stackroute.springbootzuulgatewayproxy.filter.PostFilter;
import com.stackroute.springbootzuulgatewayproxy.filter.PreFilter;
import com.stackroute.springbootzuulgatewayproxy.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SpringbootzuulgatewayproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootzuulgatewayproxyApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
