# import xlsxwriter module
import xlsxwriter
 
# Workbook() takes one, non-optional, argument  
# which is the filename that we want to create.
workbook = xlsxwriter.Workbook('Example1_chart.xlsx')
 
# The workbook object is then used to add new  
# worksheet via the add_worksheet() method.
worksheet = workbook.add_worksheet()
 
# here we create a line chart object .
chart1 = workbook.add_chart({'type': 'line'})
 
# note : spaces is not inserted in b / w
# = and Sheet1, Sheet1 and !
# if space is inserted it throws warning.
chart1.add_series({
    'categories': '=Sheet1!$A$2:$A$7',
    'values':     '=Sheet1!$B$2:$B$7',
    'trendline': {
        'type': 'polynomial',
        'order': 2,
        'line': {
            'color': 'red',
            'width': 2,
            'dash_type': 'long_dash',
        },
    },
})
 
# Configure the second series with
# a moving average trendline.
chart1.add_series({
    'categories': '=Sheet1!$A$2:$A$7',
    'values':     '=Sheet1!$C$2:$C$7',
    'trendline': {'type': 'linear'},
    'line': {
            'color': 'red',
            'width': 1,
            },
})
 
# Add a chart title.
chart1.set_title({'name': 'Exam Score Distribution'})
 
# Add x-axis label
chart1.set_x_axis({'name': 'Subjects'})
   
# Add y-axis label
chart1.set_y_axis({'name': 'Marks'})
 
# Set an Excel chart style.
chart1.set_style(11)
 
# add chart to the worksheet with given
# offset values at the top-left corner of
# a chart is anchored to cell D2
worksheet.insert_chart('D2', chart1,
    {'x_offset': 20, 'y_offset': 5})
 
# Finally, close the Excel file 
# via the close() method. 
workbook.close()