# PAYTRACKER 9000
PAYTRACKER 9000 is a simple console application that lets you write or load your payments in the format:

<em>currency code/symbol amount</em> \
or \
<em>amount currency code/symbol</em>

<u>Example:</u> \
USD 800 \
200 CZK \
$5


Supported formats of currency codes are ISO 4217. Aggregated payments are printed every minute. Write 'quit' to exit the application. 
<h3>Prerequisites</h3>
<ul>
    <li> Java 8 </li>
    <li> Apache Maven </li>   
</ul>

<h3>How to compile:</h3> 
<code>mvn clean install</code>

<h3>How to run:</h3>
<ul>
    <li> with paytracker/run.bat (windows) or paytracker/run.sh (linux) scripts or </li>
    <li> <code>java -jar paytracker-1.0.jar</code></li>
</ul>
