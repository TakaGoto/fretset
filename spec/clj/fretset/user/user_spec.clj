(ns fretset.user.user-spec
  (:require [speclj.core              :refer :all]
            [digest                   :refer [sha-256]]
            [fretset.user.user        :refer :all]
            [hyperion.api             :refer [save find-by-key]]
            [hyperion.dev.spec-helper :refer [with-memory-datastore]]))

(describe "User"
  (with-memory-datastore)
  (with valid-user
    {:first-name "George"
     :last-name "Tucker"
     :email "george@fakeemail.com"
     :password "george1" :password-confirmation "george1"})

  (it "saves the user"
    (let [george (save (user @valid-user))
          result (find-by-key (:key george))]
      (should= (:first-name @valid-user) (:first-name result))
      (should= (:email @valid-user) (:email result))
      (should-not= nil (:created-at result))
      (should-not= nil (:updated-at result))))

  (it "digests the password for security"
    (let [george (save (user @valid-user))
          result (find-by-key (:key george))
          digested-password (sha-256 (str "Fretset not Jetset!" (:password @valid-user)))]
      (should= digested-password (:password result))))

  (context "validations"
    (it "validates first name exists"
      (let [errors (validate-user (dissoc @valid-user :first-name))]
        (should= 1 (count (:first-name errors)))
        (should= ["Please input your first name"] (:first-name errors))))

    (it "validates last name exists"
      (let [errors (validate-user (dissoc @valid-user :last-name))]
        (should= 1 (count (:last-name errors)))
        (should= ["Please input your last name"] (:last-name errors))))

    (it "validates email exist"
      (let [errors (validate-user (dissoc @valid-user :email))]
        (should-contain "Please input your email" (:email errors))))

    (it "validates password exists"
      (let [errors (validate-user (dissoc @valid-user :password :password-confirmation))]
        (should-contain "Please input your password" (:password errors))))

    (it "validates password-confirmation exists"
      (let [errors (validate-user (dissoc @valid-user :password-confirmation))]
        (should-contain "Please input your password confirmation" (:password-confirmation errors))))

    (it "validates proper formatting for email"
      (let [errors (validate-user (assoc @valid-user :email "not-valid-email"))]
        (should= ["Please input a correct email format"] (:email errors))))

    (it "validates password is the same as password confirmation"
      (let [errors (validate-user (assoc @valid-user :password-confirmation "not-matching-password"))]
        (should= 1 (count (:password errors)))
        (should= ["Password and password confirmation don't match"] (:password errors))))

    (it "validates password length is at least 6"
      (let [errors (validate-user (assoc @valid-user :password-confirmation "i"
                                         :password "i"))]
        (should= 1 (count (:password errors)))
        (should= ["must have length greater than or equal to 6"] (:password errors))))))

