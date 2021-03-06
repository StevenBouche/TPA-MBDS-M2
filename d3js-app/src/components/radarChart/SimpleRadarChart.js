import React, { PureComponent } from 'react';
import { Radar, RadarChart, PolarGrid, Legend, PolarAngleAxis, PolarRadiusAxis, ResponsiveContainer } from 'recharts';

const data = [
	{
		subject: 'Puissance moyenne',
		A: 120,
		B: 110,
		fullMark: 150,
	},
	{
		subject: 'Porte',
		A: 5,
		B: 130,
		fullMark: 150,
	},
	{
		subject: 'Type prédominant',
		A: 86,
		B: 130,
		fullMark: 150,
	},
	{
		subject: 'Salaire < 50K',
		A: 99,
		B: 100,
		fullMark: 150,
	},
	{
		subject: '',
		A: 85,
		B: 90,
		fullMark: 150,
	},
	{
		subject: 'History',
		A: 65,
		B: 85,
		fullMark: 150,
	},
];

export const SimpleRadarChart = ({brand, brandCompare}) => {
	return (
		<ResponsiveContainer width="100%" height="100%">
			<RadarChart cx="50%" cy="50%" outerRadius="80%" data={data}>
				<PolarGrid />
				<PolarAngleAxis dataKey="subject" />
				<PolarRadiusAxis angle={30} domain={[0, 150]} />
				<Radar name="Mike" dataKey="A" stroke="#8884d8" fill="#8884d8" fillOpacity={0.6} />
				<Legend />
			</RadarChart>
		</ResponsiveContainer>
	);
}
