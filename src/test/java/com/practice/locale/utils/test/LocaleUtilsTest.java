package com.practice.locale.utils.test;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {
	private static final String EMPTY_STRING = "";
	private LocaleUtils localeUtils;
	private String variant;
	private String language;
	private String country;
	private String numericAreaCode;

	@Before
	public void setUp() {
		localeUtils = new LocaleUtils();
		variant = "var";
		language = "zh";
		country = "CN";
		numericAreaCode = "123";
	}

	@Test
	public void should_return_null_when_string_is_null() {
		//given

		//when
		Locale result = localeUtils.toLocale(null);
		//then
		assertThat(result).isNull();
	}

	@Test
	public void should_return_empty_locale_when_string_is_empty() {
		//given

		//when
		Locale result = localeUtils.toLocale(EMPTY_STRING);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY_STRING));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_handle_java_7_scripts_or_extensions() {
		//given
		String java7Str = "#china";
		//when
		localeUtils.toLocale(java7Str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_length_less_than_2() {
		//given
		String str = "s";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_str_less_than_3_and_start_with_underscore() {
		//given
		String str = "_s";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_start_with_underscore_and_first_two_char_is_not_upper_case() {
		//given
		String str = "_sC";
		//when
		localeUtils.toLocale(str);

		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_start_with_underscore_and_length_less_than_5() {
		//given
		String str = "_" + country + "z";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_start_with_underscore_and_4th_char_is_underscore() {
		//given
		String str = "_CNchengdu";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test
	public void should_return_empty_language_locale_when_str_length_is_3_and_start_with_underscore() {
		//given
		String str = "_" + country;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY_STRING, "CN"));
	}


	@Test
	public void should_return_locale_when_is_valid_start_with_underscore() {
		//given
		String str = "_" + country + "_" + variant;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY_STRING, country, variant));
	}

	@Test
	public void should_return_language_locale_when_str_is_ISO_639_language_code() {
		//given
		//when
		Locale result = localeUtils.toLocale(language);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language));
	}

	@Test
	public void should_return_country_language_when_str_is_ISO_639_language_and_ISO_3166_country_without_variant() {
		//given
		String str = language + "_" + country;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language, country));
	}

	@Test
	public void should_return_country_language_when_str_is_ISO_639_language_and_ISO_3166_country_with_variant() {
		//given
		String str = language + "_" + country + "_" + variant;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language, country, variant));
	}

	@Test
	public void should_return_country_language_when_str_is_ISO_639_language_and_empty_country_with_variant() {
		//given
		String str = language + "_" + EMPTY_STRING + "_" + variant;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language, EMPTY_STRING, variant));
	}

	@Test
	public void should_return_country_language_when_str_is_ISO_639_language_and_numberic_area_code_without_variant() {
		//given
		String str = language + "_" + numericAreaCode;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language, numericAreaCode));
	}

	@Test
	public void should_return_country_language_when_str_is_ISO_639_language_and_numberic_area_code_with_variant() {
		//given
		String str = language + "_" + numericAreaCode + "_" + variant;
		//when
		Locale result = localeUtils.toLocale(str);
		//then
		assertThat(result).isEqualToComparingFieldByField(new Locale(language, numericAreaCode, variant));
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_has_more_than_3_underscore() {
		//given
		String str = language + "_" + country + "_" + variant + "_";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_language_is_blank() {
		//given
		String str = " _";
		//when
		localeUtils.toLocale(str);
		//then
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_exception_when_language_is_empty() {
		//given
		String str = language + "_" + country.toLowerCase();
		//when
		localeUtils.toLocale(str);
		//then
	}
}
